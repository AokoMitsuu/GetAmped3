	� G %/accs/floating_arms/floating_arms.mot ��       �W[oG~�S#�u�ٵ�8-2A
!$�$B@UY��ؙf/��8����I)T
�BPA!�T
^���ҕM�Կ�33{3Iڈ>T��X��3�~�9s�W�A�UvQU�).ժX2k�<�;�F�w�f;���v�R֨�to"W�mnE�4���ĖD���ݶ�`Q��8��C:���y���*������bz���d`��.NqzV+�_���'�>7N�)�S�2bO�Z�\W�f2�s����1���%V9~bwv�0�o딀�_��9���s(t2"�j��gtdBC�9�R5]wՊak�Xբ昮���6�*�����f�� ��H.��bɉXT�_Z�o����啍E������֛s��N��D�i��cGJ�v� �m���hYu�($�~��Y���b�	�t��&mH"�)\2�@H���D��8{�n����5�.�bް^�8�2�����	}s�0�S�(e��#]���r��R�ea
B�5C/6�Э<�{[��ً�h����o��m��h�\i]���kT=*":�|nEHʄX����>ڻ�)J���"��l
!A����^��&�V�����˾w�_��//��?2N�Hd`�0)x��Y)�[9��|EX��R�œǢ�3u|F���9��L����'{O�������^�We�����7@i]��{��K�Zﴮ>��*��8������-K����
_!�CđX��B�l���T(�
k�gҡ��A�7�	�B�.t'�c��fC�7��W���Ё�E��go��'b�,��=�16��׌��(�D>x&7�8�ts��n��Q����U5�۟��_�/��K^��=Z�w��3���m�z���] �^�B#k_��{���������{ �G�r4d�7+���R�[�\H,�k��$�����$�ԟ�91������3�=����nh�M����@�^�	��ۛ��~��G��Z�о��IR�]�Xl�:6�`����/��Ttö��´��o�>i�ɷ�8(�!��_�n����$�t�oe���
�gE$����lc���/�c��<�؎D3D�gٳ�'�t��/�-��l���Od*��!al�`L ��y[���`����AwM�IV�S��KQ�����o��Վ�AU�O�t��/�
/;-ogx�J9 ;u�=9$,��r��+��Z{�^�.���ѷm�[~�3��/xuvx�%��4�R����L��Ӹ//��I	��d�2TB�\�Zs���\���f����>�`\e;0A��Q����:9�؂���ur��P�u�7l�h�h��`tb:z3J_��\]���?]X�9-k&Là4�ǿg	u�|��X©�
V�: �G��c��9��0�
�P�Ζ��������B8}Q�ħ�b}.!��G� ����ӫ]��l�c�����1�#��Dбd�B��I�W��)�_���Y��p�Im����%ƹ`Ӷ�⥳�{HL���xx�ݡ���_�(�l����K�f�mq]`� d����H��7	�ǎ�8:	C�E+�S���Hj�L~�Q�]��:�./% I�8��)�KΪLN�ՙS�	���^���jf�\��@>@�밡U��%������3���cC�#3#�c���;�P�ݿ?�G_�vڡt��x��WձO�=D�����'Ǎս   �  Lga2/stage/AmpedScript; e start floating_arms_explode_x_execute 
shotCalc_x (Lkotori/geom/Vector3D;)V shot_x (Lkotori/geom/Quaternion;)V broken broken_a broken_x 
setVisible (Z)V
 f p q setShadowState
 d s 3 lockItemScript (ZZ)V
 f u v
 H 0 3 lockTimeCount
 f y q finishAllConnections
 d { 1 clearGenerate
 d } 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d  � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D "/accs/floating_arms/shootR_sx1.scm�            ?�  ?�  ?�               start2 isServer ()Z
 f � � getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � length ()F
 � � �@�  �� kotori.geom.Quaternion?}�5>�e         Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � �
 f � �C4  C�  �4  	 � R  (FF)F
 � � � min
 � � �B4  A�  ��?�               Lkotori/geom/Quaternion; � clone
 � � �=��� getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � Lkotori/gfx/KGLScriptModel; � rotate (Lkotori/geom/Quaternion;I)V
 � � ���?}�5>�e         startScript (Ljava/lang/String;)Z
 � � � beam2 vibe (FFFFI)V
 d � �<#�
 	playSound
 d � 3���D /efx/gun/shell_floating_x.scm�        ����@�  @�  @�              � hit_be  d� �    a��       ,�>�         	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             beam_end
 � � 1D /accs/floating_arms/arms.scm�            ?�  ?�  ?�                getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � Lkotori/data/KGLGenerationData; � pos (FFF)V
 � � � back2_R putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms    efx_endD /accs/floating_arms/arms.scm�            ?�  ?�  ?�                f  g 1  }*� rW*� tW*� wW*d� xW*� wW*� zW*� |W*� ~W**��� �� �W* ȶ xW*� ��  L*� �M,� 
*,� �L*h+� �W*� xW*� �W*� �W�  h 1  *� �L*� xW*+� W*d� xW*� W�  i j �*� �M,��+��*� �N+-� �W+� �8��� *,� xW*�� W�+� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8+� �8+� �+� �8		� �� �� �Zd�8

�� #b
j8b
j�
� ��nj8b8

�f8�:� �:6� /�`�jj8� �W,� �W*
� xW�W
���6� 0�`�jj8� �W,� �W*
� xW�W
���*d� xW� �W,� �W
� �W*� W� *,� xW*�� W�  k l  p*� �M,� 
,Ҷ �W*^� xW*�\�� �W* ϶ �W�N6� '*+� �W*�-� �W*� �W* ȶ xW�W���,� 
,� �W�  m 1  *� W�  n 1  v*� wW*� zW*� xW*� �L+� +� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W�  o 1  x*� wW*� zW*� xW*� �L+� 
+�� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W� �$z    ?S(6���p?�L^?>t�?�[�  