	� G   ��       �U�o�6~�����B%�,�v�U�C�Aѵ(�a�-Q6aI$H:��ߑ�"9�A����������i�)�&Q�4Y��S)h��J&t|5��Rp�іk.Y�M����yMyyΜ���	���Ƙ�
�J�>�ׇ��l�6.[����@<ЊJ���!N$x��;������{ybt7���0�M�tUp�%����W4�wZ�h�6b�C���x�A��fv�թ�Nbj>�D������ў'��Γ�p��@�4�']&��?�Z��I.�B����P�iM�!� +���b'��L���b7�`�a]K:�`��H{5~�,C�������p^p���GN�[�6� Za�
��7B�Z	~��%�֨�^�ƣ�_��!e��L��0��,8%���#H#���B��h%)��-dև@��@�>Jx2�C�g �>R{
9¿ݪ�+|�k�ً[�YA_��Τ@��^���g�?}�RF�9�K�qlj{Tjl�5���v� �#�U�f�{'��D�TM��)mGމͬ�[5[ԍ�,��G6�0�����DX�	�4����[��cӹyA`��ah�f���v�i�t�?�H���>Q
/��z�n>�>X�'�I�E�l�R�t�;��I�cj �652O�+�9u?��T~��$Q�;@� �V�w���}+kf֡(ۗ�Ƨy�"�7p�9��a�1S_4-+�aH�)��1��W��m��3+a�cYۯfS���ɍ����<6��6U��+�~&j���<�$��I���5�o�/ڦXKS��?�ڄ��o=��k�
����r�Z��#3ȜK�L�ň�?�m�nn����b����Mp��oC�y�a0�� �S�糇��5����9����������u��@~!�E���<qyY�p�6A��ĥ���P^H�Xv`P
   q  Lga2/stage/AmpedScript; e type Lkotori/data/KGLGenerationData; pondD /efx/water/blood_pond.scm�            ?�  ?�  ?�               fish1D /efx/etc/efx_fish/fish_a_2.scm�            @   @   @                fish2D  /efx/etc/efx_fish/fish_a_3_3.scm�            @   @   @                fish3D /efx/etc/efx_fish/fish_c_1.scm�            @   @   @                fish4D /efx/etc/efx_fish/fish_c_3.scm�            @   @   @                redD /efx/water/dripdrop_red.ptc�            ?�  ?�  ?�                yelD /efx/water/dripdrop_yellow.ptc�            ?�  ?�  ?�                purD /efx/water/dripdrop_purple.ptc�            ?�  ?�  ?�                bluD /efx/water/dripdrop_blue.ptc�            ?�  ?�  ?�                greD /efx/water/dripdrop_green.ptc�            ?�  ?�  ?�                
rndTypeEfx (FFFFFFFFFLjava/lang/String;)V start hitstage drop_efx drop_efx_kill rndi
 H � 4 h pos (FFF)V
 � � � hpb
 � � � sx	 � �  sy	 � �  sz	 � �  generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � 	playSound
 d � 3D /accs/artist/efx_artist.scm�            ?���?@  ?���             
paint_efx2D /efx/flash/glow02.scm�            @   @   @                 
start_whi5
 H 0 3 	getAttack ()Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � getAttackContext ()Lga2/stage/atk/AttackContext;
 � � � getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � Lga2/stage/AmpedModel; � getID
 � � 2 Lga2/stage/atk/AttackContext; � getAttacker ()Lga2/stage/AmpedModel;
 � � � isItemModel (Lga2/stage/AmpedModel;)Z
 f � � Lga2/stage/ItemModel; � parentid	 � �  println (Ljava/lang/String;)V
 H � � myid: (FFF)Lkotori/geom/Vector3D;
 H  � setItem D(IILkotori/geom/Vector3D;Ljava/lang/String;ZILjava/lang/String;III)V
 f � � */efx/water/water_shockwave_color_noput.scm paint_ground kill
 f � 1 Lkotori/geom/KMath; � D2R (F)F
 � � � sin
 H � � cos
 H � � rndf
 H � �Bp  >���>L��   ?��>����H   f g  i h k h m h o h q h s h u h w h y h { h - 1  )j�l�n�p�r�t�v�x�z�	|�
�  } ~  ~� ��      #    *    1    8    ? ?�:� �:� �:� �	:� 
�
:� #$%� �W� �W� �� �	� �*
� �W�   1  �  � 1  �* ۶ �W*��� �W*� W*��� �W*�� �W*� �W*� �L+� �M*� �N-� �6,� �:*� �� :� �6*��� �W*� ���:�� �W*�� �W*� �W�  � 1  �<� �*θ ��j� ��j�θ ��j� ��j<h���ϸ �b�ϸ �b�ϸ �b� W*θ ��j� ��jθ ��j� ��j<h��bϸ �bbϸ �bbϸ �b� W�W��o�  � 1  *� W*�� �W*� �W� ��    <���\            � � changemodel� */accs/shotgun_sword/shotgun_sword_beam.mot