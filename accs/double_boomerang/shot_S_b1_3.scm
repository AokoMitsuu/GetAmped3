	� G   f�       �U�o9>�T��`R���af���f�PJ��n���VEnƙX��G�Ӕ9��nH�a�]!V���J�1�h��b���P��	�H������>�9�(��E��IE"�5z)�m�RU����a������y�=�\픹spܪ��D��6�a����)q� m��_����M}nmb7��o��1	�PuH,i�;;��Y���Pع;;S�T5�$���(�:�[jC�A*�͘��0����m�oA ���^�I�-���wcڂJz��D�-��5`I��v�\C)�.|Nu�]|!_]T<�[���8��f�I�>�1 :\�Z�LK�\2�x�������)�D�j���sv��i�P�>��z��xK�fz�Ef^N�������ǿ�������3�%��-�2b�7%�1�M��%�3 6s-�t$x?E�������OTY?y���Fe������Uv�UWƏ�8m7�:���'�8F��(�I�����XVtM��)30a��[=�˅��������,?&�Y�-H��z<~$���|�����C�[��P�>�ϗhB@�H{�4�[$�o�b�iM�OB��VY���l�{6e���~�;�����w�Z��Iŕ��]�p�T�E����ǌ0�DJ�סV�P�oX��V�:�l�����Z�n�]:�DS�6�*S��<-�}	�Q�ק [p}�Սk�Q��C#9�"�X}�[� �k��侞Ihc�2�-r��1tWb�in'���(֣�?��2�|��`��&�W.{��ǒ󒡠�1���L���ij\ܼ���sk�Jckc}g}�����4m����Z]B��4�<���OX�'(2��=ߔߟ�q��M� v��   M  Lga2/stage/AmpedScript; e isend start roll roll_end timeout hitstage hit setShadowState
 d n 3 clearGenerate
 d p 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d r s setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d u vD 5/accs/double_boomerang/double_boomerang_shot_wind.scm�            ?�  ?�  ?�  B�           roll_mp getPosition ()Lkotori/geom/Position;
 f z {1                          (FFFFFF)Lkotori/geom/Rect3D;
 ~ +  	 � Q 	 � R >�ff	 � S  getAreaPlayerID (Lkotori/geom/Rect3D;IZ)[I
 f � � length ([I)I
 H � � ([II)I
 H * � getOwnID
 f � 2 enableAttack (Z)Z
 f � � addMP
 f � 3 	playSound (II)V
 d � � setPlayerEfx (ILjava/lang/String;)V
 f � � double_boomerang
 H 0 3 (Lkotori/geom/Vector3D;I)V
 f i �-    E;�     D 0/accs/double_boomerang/double_boomerang_wind.scm�        =���?�  ?�  ?�  B�           start_illusion_mp getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � end_illusion kill
 f � 1 setFlagsToModifier (ILjava/lang/String;I)V
 f � �    f g  - 1  � �  h 1  �*� oW*� qW**xy� t� wW� �*� |L}M,+� �+� ��b+� ����� �W*,� �N-� _*-� �� V6� F*-� �6� Й 1*� �� (� *� �W*� �W*� �W*�� �W*� W��W*-� ����*2� �W��n�  i 1  !*� oW*�� �W*� qW**��� t� wW�  j 1  *� �L+� 
+�� �W*d� �W*� �W�  k 1  1*� oW*� �L+� 
+j� �W*,� �W*1��� �W*� �W�  l 1  *� W�  m 1  )*� oW*� �L+� 
+j� �W*1��� �W*� �W� ^���                        �  